package fi.dy.masa.tweakeroo.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum SnapAimMode implements IConfigOptionListEntry
{
    YAW     ("yaw",     "tweakeroo.label.snap_aim_mode.yaw"),
    PITCH   ("pitch",   "tweakeroo.label.snap_aim_mode.pitch"),
    BOTH    ("both",    "tweakeroo.label.snap_aim_mode.both");

    private final String configString;
    private final String translationKey;
    
    private static final SnapAimMode[] VALUES = values();
    private final static Map<String, SnapAimMode> FROM_NAME = Arrays.asList(VALUES).stream()
            .collect(Collectors.toMap((i) -> i.configString.toLowerCase(), (i) -> i));

    private SnapAimMode(String configString, String translationKey)
    {
        this.configString = configString;
        this.translationKey = translationKey;
    }

    @Override
    public String getStringValue()
    {
        return this.configString;
    }

    @Override
    public String getDisplayName()
    {
        return StringUtils.translate(this.translationKey);
    }

    @Override
    public IConfigOptionListEntry cycle(boolean forward)
    {
        int id = this.ordinal() + (forward ? 1:-1);

        if (id >= VALUES.length) id = 0;
        if (id < 0) id = VALUES.length - 1;

        return VALUES[id];
    }

    @Override
    public SnapAimMode fromString(String name)
    {
        return fromStringStatic(name);
    }

    public static SnapAimMode fromStringStatic(String name)
    {
        if(name == null) return SnapAimMode.YAW;
        return FROM_NAME.getOrDefault(name.toLowerCase(), SnapAimMode.YAW);
    }
}
