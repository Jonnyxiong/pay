package entity.agent;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("CloudUserOrder")
public class CloudUserOrder {
    
    // 订单id号，16位,规则：'D'+ YYMMDDHHMM（年月日时分，10位）+ 类型（1位）+ 序号（4位，0000－9999）,0：云用户，1：运营平台

    private String orderId;
    // 采购单号，对应t_sms_cloud_user_purchase表中purchase_id字段
    private String purchaseId;
    // 短信单价，单位：元,普通短信时有值

    private BigDecimal unitPrice;
    // 订单短信条数，单位：条，普通短信时有值

    private Integer orderNumber;
    // 订单金额，单位：元
    private BigDecimal orderAmount;
    // 订单状态，0：待付费，1：已付费
    private Integer orderState;
    // 使用状态，0：未使用，1：使用中，2：完成，此状态用于计费模块判断此订单是否可用
    private Integer useState;
    // 普通短信剩余条数，单位：条
    private Integer remainNumber;
    // 国际短信剩余金额，单位：元
    private BigDecimal remainAmount;
    // 备注
    private String remark;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId ;
    }
    
    public String getPurchaseId() {
        return purchaseId;
    }
    
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId ;
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice ;
    }
    
    public Integer getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber ;
    }
    
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }
    
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount ;
    }
    
    public Integer getOrderState() {
        return orderState;
    }
    
    public void setOrderState(Integer orderState) {
        this.orderState = orderState ;
    }
    
    public Integer getUseState() {
        return useState;
    }
    
    public void setUseState(Integer useState) {
        this.useState = useState ;
    }
    
    public Integer getRemainNumber() {
        return remainNumber;
    }
    
    public void setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber ;
    }
    
    public BigDecimal getRemainAmount() {
        return remainAmount;
    }
    
    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount ;
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
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime ;
    }
    
}