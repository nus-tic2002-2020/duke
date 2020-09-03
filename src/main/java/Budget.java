public class  Budget {

    //VARIABLES-----------------------------------------
    protected double budgetSet;
    protected double budgetUsed = 0;
    protected double budgetBalance;
    protected boolean isOpen = true;
    protected boolean isOverBudget;

    protected static double totalBudgetSet;
    protected static double totalBudgetUsed = 0;
    private static double totalBudgetBalance;
    private static boolean isTotalOverBudget;

    //CONSTRUCTORS--------------------------------------
    public Budget(double budgetSet) {
        this.budgetSet = budgetSet;
        totalBudgetSet = totalBudgetSet + budgetSet;
        totalBudgetBalance = totalBudgetSet - totalBudgetUsed;
        isTotalOverBudget = totalBudgetBalance < 0;
    }

    public Budget() {
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

    private void transferBalanceIn(double balanceIn) {
        this.budgetSet = this.budgetSet + balanceIn;
        this.budgetBalance = this.budgetSet - budgetUsed;
        this.isOverBudget = this.budgetBalance < 0;
    }

    public boolean transferBalanceOut(double balanceOut, Budget target) {
        if (!this.isOpen) {
            return false;
        } else if (this.budgetBalance < balanceOut) {
            return false;
        } else {
            this.budgetSet = this.budgetSet - balanceOut;
            this.budgetBalance = this.budgetSet - budgetUsed;
            target.transferBalanceIn(balanceOut);
            if (this.budgetBalance == 0) {
                this.closeBudget();
            }
            return true;
        }
    }

    public boolean closeBudget() {
        if(!this.isOverBudget){
            this.isOpen = false;
            return true;
        } else {
            return false;
        }
    }


    //GET STATEMENTS------------------------------------
    public double getBudgetSet(){
        return this.budgetSet;
    }

    public double getBudgetUsed(){
        return this.budgetUsed;
    }

    public double getBudgetBalance(){
        return this.budgetBalance;
    }

    public boolean getIsOpen(){
        return this.isOpen;
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

}
