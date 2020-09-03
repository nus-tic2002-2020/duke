public class  Budget {

    //VARIABLES-----------------------------------------
    private double budgetSet;
    private double budgetRevised;
    private double budgetUsed = 0;
    private double budgetBalance;
    private boolean isRevised = false;
    private boolean isOverBudget;

    private static double totalBudgetSet;
    private static double totalBudgetUsed = 0;
    private static double totalBudgetBalance;
    private static boolean isTotalOverBudget;

    //CONSTRUCTORS--------------------------------------
    public Budget(double budgetSet) {
        this.budgetSet = budgetSet;
        this.budgetRevised = this.budgetSet;
        totalBudgetSet = totalBudgetSet + budgetSet;
        totalBudgetBalance = totalBudgetSet - totalBudgetUsed;
        isTotalOverBudget = totalBudgetBalance < 0;
    }

    public Budget() {}

    public Budget(double budgetSet, double budgetRevised,
                  double budgetUsed, double budgetBalance,
                  boolean isRevised, boolean isOverBudget) {
        this.budgetSet = budgetSet;
        this.budgetRevised = budgetRevised;
        this.budgetUsed = budgetUsed;
        this.budgetBalance = budgetBalance;
        this.isRevised = isRevised;
        this.isOverBudget = isOverBudget;
    }

    //SET STATEMENTS------------------------------------
    public void setBudgetSet(double budgetSet) {
        this.budgetSet = budgetSet;
        this.budgetBalance = this.budgetSet - budgetUsed;
        this.isOverBudget = this.budgetBalance < 0;
        totalBudgetUsed = totalBudgetUsed + budgetUsed;
        totalBudgetBalance = totalBudgetSet - totalBudgetUsed;
        isTotalOverBudget = totalBudgetBalance < 0;
    }

    public void setBudgetUsed(double budgetUsed) {
        this.budgetUsed = budgetUsed;
        this.budgetBalance = this.budgetSet - budgetUsed;
        this.isOverBudget = this.budgetBalance < 0;
        totalBudgetUsed = totalBudgetUsed + budgetUsed;
        totalBudgetBalance = totalBudgetSet - totalBudgetUsed;
        isTotalOverBudget = totalBudgetBalance < 0;
    }

    private void transferBudgetIn(double balanceIn) {
        this.budgetRevised = this.budgetRevised + balanceIn;
        this.budgetBalance = this.budgetRevised - budgetUsed;
        this.isOverBudget = this.budgetBalance < 0;
        this.isRevised = true;
    }

    public boolean transferBudgetOut(double balanceOut, Budget target) {
        if (this.budgetBalance < balanceOut) {
            return false;
        } else {
            this.budgetRevised = this.budgetRevised - balanceOut;
            this.budgetBalance = this.budgetRevised - budgetUsed;
            target.transferBudgetIn(balanceOut);
            this.isRevised = true;
            return true;
        }
    }


    //GET STATEMENTS------------------------------------
    public double getBudgetSet(){
        return this.budgetSet;
    }

    public double getBudgetRevised(){
        return this.budgetRevised;
    }

    public double getBudgetUsed(){
        return this.budgetUsed;
    }

    public double getBudgetBalance(){
        return this.budgetBalance;
    }

    public boolean getIsRevised(){
        return this.isRevised;
    }

    public boolean getIsOverBudget(){
        return this.isOverBudget;
    }

    public static double getTotalBudgetSet(){
        return totalBudgetSet;
    }

    public static double getTotalBudgetUsed(){
        return totalBudgetUsed;
    }

    public static double getTotalBudgetBalance(){
        return totalBudgetBalance;
    }

    public static boolean getIsTotalOverBudget(){
        return isTotalOverBudget;
    }

    public String getSaveText() {
        String text = this.budgetSet + "/" +
                this.budgetRevised + "/" +
                this.budgetUsed + "/" +
                this.budgetBalance + "/" +
                this.isRevised + "/" +
                this.isOverBudget;
        return text;
    }

}
