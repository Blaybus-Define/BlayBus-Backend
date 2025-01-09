package blaybus.blaybus_backend.domain.member.entity;

public enum MemberLevel {

    F1_I(0, 13500),
    F1_II(13501, 27000),
    F2_I(27001, 39000),
    F2_II(39001, 51000),
    F2_III(51001, 63000),
    F3_I(63001, 78000),
    F3_II(78001, 93000),
    F3_III(93001, 108000),
    F4_I(108001, 126000),
    F4_II(126001, 144000),
    F4_III(144001, 162000),
    F5(162001, Integer.MAX_VALUE);

    private final int minExperience;
    private final int maxExperience;

    MemberLevel(int minExperience, int maxExperience) {
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
    }

    public static MemberLevel getLevelByExperience(int experience) {
        for (MemberLevel level : values()) {
            if (experience >= level.minExperience && experience <= level.maxExperience) {
                return level;
            }
        }
        throw new IllegalArgumentException("Experience value out of range: " + experience);
    }
}
