package fi.dy.masa.tweakeroo.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum PlacementRestrictionMode implements IConfigOptionListEntry
{
    PLANE       ("plane",       "tweakeroo.label.placement_restriction_mode.plane"),
    FACE        ("face",        "tweakeroo.label.placement_restriction_mode.face"),
    COLUMN      ("column",      "tweakeroo.label.placement_restriction_mode.column"),
    LINE        ("line",        "tweakeroo.label.placement_restriction_mode.line"),
    LAYER       ("layer",       "tweakeroo.label.placement_restriction_mode.layer"),
    DIAGONAL    ("diagonal",    "tweakeroo.label.placement_restriction_mode.diagonal");

    private final String configString;
    private final String unlocName;
    
    private final static PlacementRestrictionMode[] VALUES = values();
    private final static Map<String, PlacementRestrictionMode> FROM_NAME = Arrays.asList(VALUES).stream()
            .collect(Collectors.toMap((i) -> i.configString.toLowerCase(), (i) -> i));

    private PlacementRestrictionMode(String configString, String unlocName)
    {
        this.configString = configString;
        this.unlocName = unlocName;
    }

    @Override
    public String getStringValue()
    {
        return this.configString;
    }

    @Override
    public String getDisplayName()
    {
        return StringUtils.translate(this.unlocName);
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
    public PlacementRestrictionMode fromString(String name)
    {
        return fromStringStatic(name);
    }

    public static PlacementRestrictionMode fromStringStatic(String name)
    {
        if(name == null) return PlacementRestrictionMode.FACE;
        return FROM_NAME.getOrDefault(name.toLowerCase(), PlacementRestrictionMode.FACE);
    }
}
