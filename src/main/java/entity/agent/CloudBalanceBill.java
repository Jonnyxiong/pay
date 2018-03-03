package entity.agent;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("CloudBalanceBill")
public class CloudBalanceBill {
    
    // 业务单号，自增序列id
    private Integer id;
    // 云用户id
    private String userId;
    // 业务类型，0：线上充值，1：管理员充值，2：预付费，3：提现，4：赠送，5：扣减，6：供应商月结
    private Integer paymentType;
    // 财务类型，0：入账，1：出账
    private Integer financialType;
    // 金额，单位：元
    private BigDecimal amount;
    // 余额剩余，单位：元
    private BigDecimal balance;
    // 订单id，payment_type=2时关联t_sms_cloud_user_order表中order_id字段

    private String orderId;
    // 操作员id，关联t_sms_user表中id字段
    private Long adminId;
    // 备注
    private String remark;
    // 生成时间
    private Date createTime;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id ;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId ;
    }
    
    public Integer getPaymentType() {
        return paymentType;
    }
    
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType ;
    }
    
    public Integer getFinancialType() {
        return financialType;
    }
    
    public void setFinancialType(Integer financialType) {
        this.financialType = financialType ;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount ;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance ;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId ;
    }
    
    public Long getAdminId() {
        return adminId;
    }
    
    public void setAdminId(Long adminId) {
        this.adminId = adminId ;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark ;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime ;
    }
    
}