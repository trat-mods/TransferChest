package net.transferchest.mod.gui.widget;

import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.InputResult;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class WReleaseFocusListPanel extends WListPanel {
    /**
     * Constructs a list panel.
     *
     * @param data the list data
     * @param supplier the widget supplier that creates unconfigured widgets
     * @param configurator the widget configurator that configures widgets to display the passed data
     */
    public WReleaseFocusListPanel(List data, Supplier supplier, BiConsumer configurator) {
        super(data, supplier, configurator);
        scrollBar = new WReleaseFocusScrollBar(Axis.VERTICAL);
    }

    @Override
    public InputResult onClick(int x, int y, int button) {
        super.onClick(x, y, button);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).releaseFocus();
        }
        return InputResult.PROCESSED;
    }
}
