package blaybus.blaybus_backend.domain.member.entity;

import blaybus.blaybus_backend.domain.auth.exception.AuthException;

import static blaybus.blaybus_backend.global.exception.ErrorCode.INVALID_JOB_ROLE;

public enum JobRoleType {
    FIELD("FIELD"),
    BRANDING("BRANDING"),
    GROWTH_STRATEGY("GROWTH_STRATEGY"),
    TECHNICAL("TECHNICAL");

    private final String value;

    JobRoleType(String value) {
        this.value = value;
    }

    public static JobRoleType fromString(String value) {
        for (JobRoleType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new AuthException(INVALID_JOB_ROLE);
    }
}
