package org.rspeer.scripts.wintertodt.data;

import org.rspeer.game.component.tdi.Skill;
import org.rspeer.game.component.tdi.Skills;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum Axe {

    BRONZE("Bronze axe", 1, Constant.BRONZE_AXE),
    STEEL("Steel axe", 6, Constant.STEEL_AXE),
    MITHRIL("Mithril axe", 21, Constant.MITHRIL_AXE),
    ADAMANT("Adamant axe", 31, Constant.ADAMANT_AXE),
    RUNE("Rune axe", 41, Constant.RUNE_AXE),
    DRAGON("Dragon axe", 61, Constant.DRAGON_AXE);

    public static final int[] IDS = Stream.of(Axe.values()).mapToInt(Axe::getId).toArray();

    private final String name;
    private final int level;
    private final int id;

    Axe(String name, int level, int id) {
        this.name = name;
        this.level = level;
        this.id = id;
    }

    public static List<Axe> getUsable() {
        int level = Skills.getCurrentLevel(Skill.WOODCUTTING);

        List<Axe> axes = new ArrayList<>();
        for (Axe axe : Axe.values()) {
            if (axe.level <= level) {
                axes.add(axe);
            }
        }

        return axes;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
