package net.transferchest.mod.gui.widget;

import io.github.cottonmc.cotton.gui.widget.WScrollBar;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.InputResult;

public class WReleaseFocusScrollBar extends WScrollBar {
    public WReleaseFocusScrollBar(Axis axis) {
        super(axis);
    }

    public WReleaseFocusScrollBar() {
        super();
    }

    @Override
    public InputResult onClick(int x, int y, int button) {
        super.onClick(x, y, button);
        releaseFocus();
        return InputResult.PROCESSED;
    }
}
