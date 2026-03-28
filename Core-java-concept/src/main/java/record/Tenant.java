package record;

public class Tenant {
    private Integer tenantId;
    private String tenantName;
    private boolean isInHouse;

    public Integer getTenantId() {
        return tenantId;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "tenantId=" + tenantId +
                ", tenantName='" + tenantName + '\'' +
                ", isInHouse=" + isInHouse +
                '}';
    }

    public Tenant(Integer tenantId, String tenantName, boolean isInHouse) {
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.isInHouse = isInHouse;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public boolean isInHouse() {
        return isInHouse;
    }

    public void setInHouse(boolean inHouse) {
        isInHouse = inHouse;
    }
}
