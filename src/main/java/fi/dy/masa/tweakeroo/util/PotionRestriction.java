package fi.dy.masa.tweakeroo.util;

import java.util.List;
import java.util.Set;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import fi.dy.masa.malilib.util.restrictions.UsageRestriction;
import fi.dy.masa.tweakeroo.Tweakeroo;

public class PotionRestriction extends UsageRestriction<StatusEffect>
{
    @Override
    protected void setValuesForList(Set<StatusEffect> set, List<String> names)
    {
        for (String name : names)
        {
            try
            {
                set.add(Registry.STATUS_EFFECT.get(
                        /* Errors here if the effect name is invalid*/
                        new Identifier(name)
                ));
            }
            catch (Exception e)
            {
                Tweakeroo.logger.warn("Invalid potion effect name '{}'", name);
            }
        }
    }
}
