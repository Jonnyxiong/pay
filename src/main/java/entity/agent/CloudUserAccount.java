package entity.agent;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Alias("CloudUserAccount")
public class CloudUserAccount {
    
    // 云用户id，关联t_sms_cloud_user_info表user_id字段
    private String userId;
    // 账户余额(元)，可为负值
    private BigDecimal balance;
    // 信用额度(元)，0为不可透支， > 0 为可透支金额
    private BigDecimal creditBalance;
    // 押金(元)
    private BigDecimal deposit;
    // 历史充值总额(元)
    private BigDecimal accumulatedRecharge;
    // 累计消费总额(元)
    private BigDecimal accumulatedConsume;
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId ;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance ;
    }
    
    public BigDecimal getCreditBalance() {
        return creditBalance;
    }
    
    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance ;
    }
    
    public BigDecimal getDeposit() {
        return deposit;
    }
    
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit ;
    }
    
    public BigDecimal getAccumulatedRecharge() {
        return accumulatedRecharge;
    }
    
    public void setAccumulatedRecharge(BigDecimal accumulatedRecharge) {
        this.accumulatedRecharge = accumulatedRecharge ;
    }
    
    public BigDecimal getAccumulatedConsume() {
        return accumulatedConsume;
    }
    
    public void setAccumulatedConsume(BigDecimal accumulatedConsume) {
        this.accumulatedConsume = accumulatedConsume ;
    }
    
}