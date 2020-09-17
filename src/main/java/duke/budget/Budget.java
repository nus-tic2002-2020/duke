package duke.budget;

/**
 * An object that records and measures the budget performance of a task.
 *
 * To create a {@code Budget} object, a budget amount must minimally be set.
 * The {@code Budget} object can thereafter record revisions, utilisation and the residual balance.
 * Class-level members are available to aggregate the total budget set, used and remaining across all Budget objects created.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Budget {

    //VARIABLES-----------------------------------------
    private double budgetSet;
    private double budgetRevised;
    private double budgetUsed = 0;
    private double budgetBalance = 0;
    private boolean isRevised = false;
    private boolean isOverBudget;

    private static double totalBudgetSet;
    private static double totalBudgetUsed = 0;
    private static double totalBudgetBalance = 0;
    private static boolean isTotalOverBudget;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Budget} object.
     *
     * @param budgetSet The initial budget amount set.
     */
    public Budget(double budgetSet) {
        this.budgetSet = budgetSet;
        this.budgetRevised = this.budgetSet;
        totalBudgetSet = totalBudgetSet + budgetSet;
    }

    /**
     * This method is used to initialise a {@code Budget} object.
     *
     */
    public Budget() {}

    /**
     * This method is used to construct a {@code Budget} object based on information from a saved file.
     *
     * @param budgetSet The initial budget amount set.
     * @param budgetRevised The revised budget amount.
     * @param budgetUsed The amount of budget utilised.
     * @param budgetBalance The amount of budget not utilised.
     * @param isRevised Indicator of whether the budget set has been revised.
     * @param isOverBudget Indicator of whether the budget set or revised has been exceeded.
     */
    public Budget(double budgetSet, double budgetRevised,
                  double budgetUsed, double budgetBalance,
                  boolean isRevised, boolean isOverBudget) {
        this.budgetSet = budgetSet;
        this.budgetRevised = budgetRevised;
        this.budgetUsed = budgetUsed;
        this.budgetBalance = budgetBalance;
        this.isRevised = isRevised;
        this.isOverBudget = isOverBudget;
        totalBudgetSet = totalBudgetSet + budgetSet;
        totalBudgetUsed = totalBudgetUsed + budgetUsed;
        totalBudgetBalance = totalBudgetBalance + budgetBalance;
        isTotalOverBudget = totalBudgetBalance < 0;
    }

    //SET STATEMENTS------------------------------------
    /**
     * This method is used to set or reset the initial budget amount.
     *
     * @param budgetSet The initial budget amount set.
     */
    public void setBudgetSet(double budgetSet) {
        this.budgetSet = budgetSet;
        this.budgetBalance = this.budgetSet - budgetUsed;
        this.isOverBudget = this.budgetBalance < 0;
        totalBudgetSet = totalBudgetSet + budgetSet;
    }

    /**
     * This method is used to record the amount of budget used upon completion of the task.
     *
     * @param budgetUsed The amount of budget utilised.
     */
    public void setBudgetUsed(double budgetUsed) {
        this.budgetUsed = budgetUsed;
        this.budgetBalance = this.budgetRevised - budgetUsed;
        this.isOverBudget = this.budgetBalance < 0;
        totalBudgetUsed = totalBudgetUsed + budgetUsed;
        totalBudgetBalance = totalBudgetBalance + this.budgetBalance;
        isTotalOverBudget = totalBudgetBalance < 0;
    }

    /**
     * This method is used to effect an inward transfer of a budget amount, thereby revising and increasing the budget available.
     * This method can only be called by a successful {@code transferBudgetOut} method, ensuring that an existing budget amount is available to effect the transfer.
     *
     * @param balanceIn The budget amount to be transferred in.
     */
    private void transferBudgetIn(double balanceIn) {
        totalBudgetBalance = totalBudgetBalance - this.budgetBalance;
        this.budgetRevised = this.budgetRevised + balanceIn;
        if(budgetUsed != 0){
            this.budgetBalance = this.budgetRevised - budgetUsed;
            totalBudgetBalance = totalBudgetBalance + this.budgetBalance;
        }
        this.isOverBudget = this.budgetBalance < 0;
        this.isRevised = true;
    }

    /**
     * This method is used to effect an outward transfer of a budget amount to another {@code Budget} object, thereby revising and decreasing the budget available.
     * The transfer might fail if there is an insufficient budget amount or budget balance in the {@code Budget} object to effect the transfer.
     *
     * @param balanceOut The budget amount to be transferred out.
     * @param target The {@code Budget} object to which the budget amount is to be transferred to.
     * @return boolean True if the transfer is successful.
     */
    public boolean transferBudgetOut(double balanceOut, Budget target) {
        if(this.budgetUsed == 0 && this.budgetRevised < balanceOut) {
                System.out.println("\tThere is insufficient budget set in the originating budget\n" +
                        "\tto effect the transfer.");
                return false;
        } else if (this.budgetUsed > 0 && this.budgetBalance < balanceOut) {
                System.out.println("\tThere is insufficient budget balance in the originating budget\n" +
                        "\tto effect the transfer.");
                return false;
        }

        totalBudgetBalance = totalBudgetBalance - this.budgetBalance;
        this.budgetRevised = this.budgetRevised - balanceOut;
        if(budgetUsed != 0){
            this.budgetBalance = this.budgetRevised - budgetUsed;
            totalBudgetBalance = totalBudgetBalance + this.budgetBalance;
        }
        this.isRevised = true;
        target.transferBudgetIn(balanceOut);
        System.out.println("\tThe budget transfer is successful.");
        return true;
    }

    /**
     * This method is used to remove the {@code Budget} object upon removal of its affiliated note.
     * The method also updates class-level members to reflect the removal of the {@code Budget} object.
     */
    public void deleteExistingBudget() {
        totalBudgetSet = totalBudgetSet - this.budgetSet;
        totalBudgetUsed = totalBudgetUsed - this.budgetUsed;
        totalBudgetBalance = totalBudgetBalance - this.budgetBalance;
    }


    //GET STATEMENTS------------------------------------
    /**
     * This method is used to retrieve the initial budget set.
     *
     * @return double The initial budget amount set.
     */
    public double getBudgetSet(){
        return this.budgetSet;
    }

    /**
     * This method is used to retrieve the revised budget amount.
     *
     * @return double The revised budget amount.
     */
    public double getBudgetRevised(){
        return this.budgetRevised;
    }

    /**
     * This method is used to retrieve the amount of budget utilised.
     *
     * @return double The amount of budget utilised.
     */
    public double getBudgetUsed(){
        return this.budgetUsed;
    }

    /**
     * This method is used to retrieve the amount of budget not utilised.
     *
     * @return double The amount of budget not utilised.
     */
    public double getBudgetBalance(){
        return this.budgetBalance;
    }

    /**
     * This method is to retrieve the revision status of the {@code Budget} object (i.e. whether the initial budget amount has been revised).
     *
     * @return boolean True if budget set has been revised.
     */
    public boolean getIsRevised(){
        return this.isRevised;
    }

    /**
     * This method is to retrieve the utilisation status of the {@code Budget} object (i.e. whether the amount budget used has exceeded the budget amount set or revised).
     *
     * @return boolean True if the budget set or revised has been exceeded.
     */
    public boolean getIsOverBudget(){
        return this.isOverBudget;
    }

    /**
     * This method exports the {@code Budget} object as a string in a format that is readable and re-constructable as a {@code Budget} object.
     *
     * @return String The {@code Budget} object as a string in a format readable and re-constructable as a Budget object.
     */
    public String getSaveText() {
        String text = this.budgetSet + "/" +
                this.budgetRevised + "/" +
                this.budgetUsed + "/" +
                this.budgetBalance + "/" +
                this.isRevised + "/" +
                this.isOverBudget;
        return text;
    }

    /**
     * This method returns as a String a report on the budget utilisation status and budget balance.
     *
     * @return String A report on the budget utilisation status and budget balance.
     */
    public String getWithinBudget() {
        if(this.isOverBudget){
            return "\u26A0\t$" + String.format("%,14.2f",
                    Math.abs(this.budgetBalance)) + " over budget.";
        } else if(this.budgetBalance == 0) {
            return "\u26A1\t$" + String.format("%,14.2f", 0.00) + " right on budget!";
        } else {
            return "\u263A\t$" + String.format("%,14.2f",
                    Math.abs(this.budgetBalance)) + " under budget.";
        }
    }

    /**
     * This method prints a report of the overall budget utilisation status and budget balance across all {@code Budget} objects.
     */
    public static void printBudgetReport(){
        String budgetReport = "$" + String.format("%,14.2f", Math.abs(totalBudgetBalance));
        if(isTotalOverBudget){
            System.out.println(String.format("%1$s%2$22s", "\t\t  a budget overrun of", budgetReport.toString()));
        } else if(totalBudgetBalance == 0) {
            System.out.println(String.format("%1$s%2$22s", "\t\t  a balanced budget balance of", budgetReport.toString()));
        } else {
            System.out.println(String.format("%1$s%2$22s", "\t\t  a healthy budget balance of", budgetReport.toString()));
        }
    }
}
