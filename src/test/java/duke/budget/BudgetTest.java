package duke.budget;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BudgetTest {

    private Budget budgetX;
    private Budget budgetY;
    private Budget budgetZ;

    @BeforeEach
    void init() {
        budgetX = new Budget(1500.50);
        budgetY = new Budget(7500.25);
        budgetZ = new Budget(3725.75);
    }

    @Test
    @DisplayName("Total Budget Test")
    void totalBudgetTest(){

        budgetX.setBudgetUsed(2538.50);
        budgetY.setBudgetUsed(3258.55);
        assertTrue(budgetX.getIsOverBudget());
        assertFalse(budgetY.getIsOverBudget());
        assertFalse(budgetZ.getIsOverBudget());
        assertEquals(1500.50, budgetX.getBudgetSet());
        assertEquals(7500.25, budgetY.getBudgetSet());
        assertEquals(3725.75, budgetZ.getBudgetSet());
        assertEquals(12726.50, Budget.getTotalBudgetSet());
        assertEquals(5797.05, Budget.getTotalBudgetUsed());
        assertEquals(3203.70, Budget.getTotalBudgetBalance());
        assertFalse(Budget.isIsTotalOverBudget());
    }

    @Test
    @DisplayName("Delete Budget Test")
    void deleteBudgetTest(){

        budgetX.setBudgetUsed(2538.50);
        budgetY.setBudgetUsed(3258.55);
        budgetY.deleteExistingBudget();
        assertTrue(budgetX.getIsOverBudget());
        assertFalse(budgetZ.getIsOverBudget());
        assertEquals(1500.50, budgetX.getBudgetSet());
        assertEquals(3725.75, budgetZ.getBudgetSet());
        assertEquals(5226.25, Budget.getTotalBudgetSet());
        assertEquals(2538.50, Budget.getTotalBudgetUsed());
        assertEquals(-1038, Budget.getTotalBudgetBalance());
        assertTrue(Budget.isIsTotalOverBudget());
    }

    @Test
    @DisplayName("Unused Budget Transfer Test")
    void unusedBudgetTransferTest(){

        budgetX.transferBudgetOut(500.25, budgetY);
        assertFalse(budgetX.getIsOverBudget());
        assertFalse(budgetY.getIsOverBudget());
        assertEquals(1500.50, budgetX.getBudgetSet());
        assertEquals(1000.25, budgetX.getBudgetRevised());
        assertEquals(7500.25, budgetY.getBudgetSet());
        assertEquals(8000.50, budgetY.getBudgetRevised());
        assertTrue(budgetX.getIsRevised());
        assertTrue(budgetY.getIsRevised());
    }

    @Test
    @DisplayName("Over Budget Transfer Test")
    void overBudgetTransferTest(){

        budgetX.setBudgetUsed(1600.50);
        assertTrue(budgetX.getIsOverBudget());
        assertFalse(budgetY.getIsOverBudget());
        budgetX.transferBudgetOut(500.25, budgetY);
        assertEquals(1500.50, budgetX.getBudgetSet());
        assertEquals(1500.50, budgetX.getBudgetRevised());
        assertEquals(7500.25, budgetY.getBudgetSet());
        assertEquals(7500.25, budgetY.getBudgetRevised());
        assertFalse(budgetX.getIsRevised());
        assertFalse(budgetY.getIsRevised());
    }

    @Test
    @DisplayName("Under Budget Transfer Test")
    void underBudgetTransferTest(){

        budgetX.setBudgetUsed(225.30);
        assertFalse(budgetX.getIsOverBudget());
        assertFalse(budgetY.getIsOverBudget());
        budgetX.transferBudgetOut(500.25, budgetY);
        assertEquals(1500.50, budgetX.getBudgetSet());
        assertEquals(1000.25, budgetX.getBudgetRevised());
        assertEquals(7500.25, budgetY.getBudgetSet());
        assertEquals(8000.50, budgetY.getBudgetRevised());
        assertTrue(budgetX.getIsRevised());
        assertTrue(budgetY.getIsRevised());
    }

}
