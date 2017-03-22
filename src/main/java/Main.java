import business_logic.ReportGenerator;
import model.instruction.Instruction;
import utils.FakeInstructionsGenerator;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final Set<Instruction> instructions = FakeInstructionsGenerator.getFakeInstructions();
        final ReportGenerator reportGenerator = new ReportGenerator();

        System.out.println(reportGenerator.generateInstructionsReport(instructions));
    }
}
