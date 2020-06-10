package net.novaborn.takeaway.user.service;


/**
 * <p>
 * 微信相关服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IWxService {
    /**
     * 保存微信小程序登陆传过来的sessionKey
     *
     * @param openId
     * @param sessionKey
     */
    void setSessionKey(String openId, String sessionKey);

    /**
     * 获取微信小程序登陆传过来的sessionKey
     *
     * @param openId
     */
    String getSessionKey(String openId);
}
