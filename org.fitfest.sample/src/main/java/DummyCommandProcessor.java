import org.fest.swing.fixture.FrameFixture;
import org.fitfest.core.RowHandler;
import org.fitfest.core.commandprocessor.CommandProcessor;


public class DummyCommandProcessor implements CommandProcessor {

	public String getCommandString() {
		return "dummy";
	}

	public void handleRow(FrameFixture window, RowHandler rowHandler) {
	}

}
