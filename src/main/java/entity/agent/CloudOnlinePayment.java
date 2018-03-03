package entity.agent;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("CloudOnlinePayment")
public class CloudOnlinePayment {
    
    // 支付id，15位，规则：'Z'+ YYMMDDHHMM（年月日时分，10位）+ 序号（4位，0000－9999）

    private String paymentId;
    // 云用户id，关联t_sms_cloud_user_info表中user_id字段
    private String userId;
    // 流水号，支付宝、微信或银联返回的流水号
    private String flowId;
    // 支付金额，单位：元
    private BigDecimal paymentAmount;
    // 支付方式，0：支付宝， 1：微信支付，2：银联支付
    private Integer paymentMode;
    // 支付时间
    private Date createTime;
    // 订单id号，对应t_sms_cloud_user_order表中order_id字段，仅充值时填空，预付费时有值
    private String orderId;
    // 支付状态，0：未支付，1：支付已提交，2：已支付，3：支付失败
    private Integer paymentState;
    // 备注
    private String remark;
    // 更新时间
    private Date updateTime;
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId ;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId ;
    }
    
    public String getFlowId() {
        return flowId;
    }
    
    public void setFlowId(String flowId) {
        this.flowId = flowId ;
    }
    
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }
    
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount ;
    }
    
    public Integer getPaymentMode() {
        return paymentMode;
    }
    
    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode ;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime ;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId ;
    }
    
    public Integer getPaymentState() {
        return paymentState;
    }
    
    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState ;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark ;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime ;
    }
    
}