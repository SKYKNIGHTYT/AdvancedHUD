package net.skyknightyt.advanced.hud.config.data;

public class ArmorSettingsData {
    public enum EmptyArmorSlotBehaviour {

        SHOW("Show"),
        DONT_SHOW("Hide"),
        MAKE_TRANSPARENT("Transparent");

        private final String label;

        EmptyArmorSlotBehaviour(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }
    public EmptyArmorSlotBehaviour emptyArmorSlotBehaviour =
            EmptyArmorSlotBehaviour.SHOW;
}
