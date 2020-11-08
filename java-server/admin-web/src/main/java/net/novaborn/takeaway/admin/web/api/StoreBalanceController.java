package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.WithdrawWrapper;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.store.entity.Balance;
import net.novaborn.takeaway.store.entity.Withdraw;
import net.novaborn.takeaway.store.enums.WithdrawState;
import net.novaborn.takeaway.store.service.impl.BalanceLogService;
import net.novaborn.takeaway.store.service.impl.BalanceService;
import net.novaborn.takeaway.store.service.impl.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/store/balance")
public class StoreBalanceController extends BaseController {
    private BalanceService balanceService;

    private BalanceLogService balanceLogService;

    private WithdrawService withdrawService;

    @GetMapping("getMyBalance")
    public ResponseEntity getMyBalance() {
        Balance balance = balanceService.getById(sysContext.getCurrentStoreId());
        return ResponseEntity.ok(balance);
    }

    @PostMapping("log/getListByPage")
    public ResponseEntity getBalanceLogListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        args.put("storeId", sysContext.getCurrentStoreId());
        page = (Page) balanceLogService.getListByPage(page, args);
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("withdraw/apply")
    public Tip applyWithdraw(@Validated Withdraw withdraw) {
        withdraw.setStoreId(sysContext.getCurrentStoreId());
        withdrawService.apply(withdraw);
        return new SuccessTip();
    }

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("withdraw/cancel")
    public Tip cancelWithdraw(Long id) {
        Optional<Withdraw> withdraw = Optional.ofNullable(withdrawService.getById(id));
        if (withdraw.isEmpty()) {
            return new ErrorTip(-1, "没有此提现申请!");
        }

        if (withdraw.get().getState() != WithdrawState.WAITING_PROCESS) {
            return new ErrorTip(-1, "当前状态无法操作，请检查!");
        }

        withdraw.get().setState(WithdrawState.CANCEL);
        withdrawService.updateById(withdraw.get());

        long afterMoney = balanceService.add(withdraw.get().getStoreId(), (long) withdraw.get().getMoney());
        balanceLogService.setMoneyLog(withdraw.get().getStoreId(), (long) withdraw.get().getMoney(), afterMoney, 4, withdraw.get().getMoney());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("withdraw/delete")
    public Tip deleteWithdraw(Long id) {
        Optional<Withdraw> withdraw = Optional.ofNullable(withdrawService.getById(id));
        if (withdraw.isEmpty()) {
            return new ErrorTip(-1, "没有此提现申请!");
        }

        if (withdraw.get().getState() != WithdrawState.CANCEL && withdraw.get().getState() != WithdrawState.REJECT) {
            return new ErrorTip(-1, "当前状态无法操作，请检查!");
        }

        withdrawService.removeById(id);
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("withdraw/handle")
    public Tip handleWithdraw(Long id) {
        Optional<Withdraw> withdraw = Optional.ofNullable(withdrawService.getById(id));
        if (withdraw.isEmpty()) {
            return new ErrorTip(-1, "没有此提现申请!");
        }

        if (withdraw.get().getState() != WithdrawState.WAITING_PROCESS) {
            return new ErrorTip(-1, "当前状态无法操作，请检查!");
        }

        withdraw.get().setState(WithdrawState.FINISH);
        withdrawService.updateById(withdraw.get());
        return new SuccessTip();
    }

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("withdraw/reject")
    public Tip rejectWithdraw(Long id, String ps) {
        Optional<Withdraw> withdraw = Optional.ofNullable(withdrawService.getById(id));
        if (withdraw.isEmpty()) {
            return new ErrorTip(-1, "没有此提现申请!");
        }

        if (withdraw.get().getState() != WithdrawState.WAITING_PROCESS) {
            return new ErrorTip(-1, "当前状态无法操作，请检查!");
        }

        withdraw.get().setPs(ps);
        withdraw.get().setState(WithdrawState.REJECT);
        withdrawService.updateById(withdraw.get());

        long afterMoney = balanceService.add(withdraw.get().getStoreId(), (long) withdraw.get().getMoney());
        balanceLogService.setMoneyLog(withdraw.get().getStoreId(), (long) withdraw.get().getMoney(), afterMoney, 4, withdraw.get().getMoney());
        return new SuccessTip();
    }

    @PostMapping("withdraw/log/getListByPage")
    public ResponseEntity getWithdrawLogListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        args.put("storeId", sysContext.getCurrentStoreId());
        if (StrUtil.isNotBlank((String) args.get("state"))) {
            args.put("state", (WithdrawState.valueOf((String) args.get("state"))));
        }

        page = (Page) withdrawService.getListByPage(page, args);
        page.setRecords((List) new WithdrawWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }
}