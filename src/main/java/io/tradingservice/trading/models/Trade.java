package io.tradingservice.trading.models;

public class Trade {

    // @Id
    // private Timestamp timeStamp;

    private float avgNav;
    private String status;
    private float quantity;
    private String fundNumber;
    private String fundName;
    private String invManager;
    private int setCycle;
    private float sAndPRating;
    private float moodysRating;

    public Trade(float avgNav, String status, float quantity, String fundNumber, String fundName,
                 String invManager, int setCycle, float sAndPRating, float moodysRating) {
        this.avgNav = avgNav;
        this.status = status;
        this.quantity = quantity;
        this.fundNumber = fundNumber;
        this.fundName = fundName;
        this.invManager = invManager;
        this.setCycle = setCycle;
        this.sAndPRating = sAndPRating;
        this.moodysRating = moodysRating;
    }


    public float getAvgNav() {
        return avgNav;
    }

    public void setAvgNav(float avgNav) {
        this.avgNav = avgNav;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getFundNumber() {
        return fundNumber;
    }

    public void setFundNumber(String fundNumber) {
        this.fundNumber = fundNumber;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getInvManager() {
        return invManager;
    }

    public void setInvManager(String invManager) {
        this.invManager = invManager;
    }

    public int getSetCycle() {
        return setCycle;
    }

    public void setSetCycle(int setCycle) {
        this.setCycle = setCycle;
    }

    public float getsAndPRating() {
        return sAndPRating;
    }

    public void setsAndPRating(float sAndPRating) {
        this.sAndPRating = sAndPRating;
    }

    public float getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(float moodysRating) {
        this.moodysRating = moodysRating;
    }
}
