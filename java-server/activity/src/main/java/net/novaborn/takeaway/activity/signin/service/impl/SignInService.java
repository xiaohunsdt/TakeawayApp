package net.novaborn.takeaway.activity.signin.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import net.novaborn.takeaway.activity.signin.service.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class SignInService implements ISignInService {
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Optional<SignIn> getSignIn(String userId, Date date) {
        String key = getKeyStr(userId, date);
        return Optional.ofNullable((SignIn) redisTemplate.opsForValue().get(key));
    }

    @Override
    public void saveSignIn(String userId, Date date, SignIn signIn) {
        String key = getKeyStr(userId, date);
        redisTemplate.opsForValue().set(key, signIn, 31, TimeUnit.DAYS);
    }

    @Override
    public void signIn(String userId, Date date) {
        Optional<SignIn> signIn = getSignIn(userId, date).or(() -> {
            SignIn temp = new SignIn();
            temp.setUserId(userId);
            temp.setRecord(0);
            temp.setCreateDate(new Date());
            return Optional.of(temp);
        });

        int record = signIn.get().getRecord();
        record |= 1 << (DateUtil.dayOfMonth(date) - 1);

        signIn.get().setRecord(record);
        this.saveSignIn(userId, date, signIn.get());
    }

    @Override
    public boolean checkSignIn(String userId, Date date, int dateUnit) {
        Optional<SignIn> signIn = getSignIn(userId, date);
        if (signIn.isEmpty()) {
            return false;
        }

        return checkSignIn(signIn.get(), date, dateUnit);
    }

    @Override
    public boolean checkSignIn(SignIn signIn, Date date, int dateUnit) {
        int dayCountOfMonth = DateUtil.endOfMonth(date).getField(DateField.DAY_OF_MONTH);

        int record = signIn.getRecord();
        boolean isSignIned = false;

        switch (dateUnit) {
            case Calendar.DAY_OF_MONTH:
                isSignIned = (record & (1 << (DateUtil.dayOfMonth(date) - 1))) > 0;
                break;
            case Calendar.WEEK_OF_MONTH:
                int day0fWeek = DateUtil.dayOfWeek(date) - 1 == 0 ? 7 : DateUtil.dayOfWeek(date) - 1;
                int day0fMonth = DateUtil.dayOfMonth(date);
                int dayOfWeekStart = day0fMonth - day0fWeek + 1;
                int dayOfWeekEnd = day0fMonth - day0fWeek + 7;
                for (int i = dayOfWeekStart; i <= dayOfWeekEnd; i++) {
                    if (i <= 0) {
                        isSignIned = this.checkSignIn(signIn.getUserId(), DateUtil.beginOfMonth(date).offset(DateField.DAY_OF_YEAR, i - 1), Calendar.DAY_OF_MONTH);
                    } else if (i > dayCountOfMonth) {
                        isSignIned = this.checkSignIn(signIn.getUserId(), DateUtil.endOfMonth(date).offset(DateField.DAY_OF_YEAR, i - dayCountOfMonth), Calendar.DAY_OF_MONTH);
                    } else {
                        if ((record & (1 << (i - 1))) <= 0) {
                            isSignIned = false;
                        } else {
                            isSignIned = true;
                        }
                    }

                    if (!isSignIned) {
                        break;
                    }
                }
                break;
            case Calendar.MONTH:
                for (int i = 0; i < dayCountOfMonth; i++) {
                    if ((record & (1 << i)) <= 0) {
                        isSignIned = false;
                        break;
                    } else {
                        isSignIned = true;
                    }
                }
                break;
            default:
                break;
        }

        return isSignIned;
    }

    @Override
    public int getSignInedCount(String userId, Date date, int dateUnit) {
        SignIn signIn = getSignIn(userId, date).orElse(null);
        if (signIn == null) {
            return 0;
        }

        int count = 0;
        int dayCountOfMonth = DateUtil.endOfMonth(date).getField(DateField.DAY_OF_MONTH);

        int record = signIn.getRecord();
        boolean isSignIned = false;

        switch (dateUnit) {
            case Calendar.DAY_OF_MONTH:
                isSignIned = (record & (1 << (DateUtil.dayOfMonth(date) - 1))) > 0;
                if (isSignIned) {
                    count++;
                }
                break;
            case Calendar.WEEK_OF_MONTH:
                int day0fWeek = DateUtil.dayOfWeek(date) - 1 == 0 ? 7 : DateUtil.dayOfWeek(date) - 1;
                int day0fMonth = DateUtil.dayOfMonth(date);
                int dayOfWeekStart = day0fMonth - day0fWeek + 1;
                int dayOfWeekEnd = day0fMonth - day0fWeek + 7;
                for (int i = dayOfWeekStart; i <= dayOfWeekEnd; i++) {
                    if (i <= 0) {
                        isSignIned = this.checkSignIn(signIn.getUserId(), DateUtil.beginOfMonth(date).offset(DateField.DAY_OF_YEAR, i - 1), Calendar.DAY_OF_MONTH);
                    } else if (i > dayCountOfMonth) {
                        isSignIned = this.checkSignIn(signIn.getUserId(), DateUtil.endOfMonth(date).offset(DateField.DAY_OF_YEAR, i - dayCountOfMonth), Calendar.DAY_OF_MONTH);
                    } else {
                        if ((record & (1 << (i - 1))) <= 0) {
                            isSignIned = false;
                        } else {
                            isSignIned = true;
                        }
                    }

                    if (isSignIned) {
                        count++;
                    }
                }
                break;
            case Calendar.MONTH:
                for (int i = 0; i < dayCountOfMonth; i++) {
                    if ((record & (1 << i)) > 0) {
                        count++;
                    }
                }
                break;
            default:
                break;
        }

        return count;
    }

    @Override
    public int[] getAllSignInedDays(int record) {
        int[] recordArr = new int[31];

        for (int i = 0; i < 31; i++) {
            if ((record & (1 << i)) > 0) {
                recordArr[i] = 1;
            } else {
                recordArr[i] = 0;
            }
        }

        return recordArr;
    }
}
