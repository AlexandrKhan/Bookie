package edu.epam.bookie.model;

/**
 * User status
 * NOT_ACTIVATED - after registration, before email confirmation
 * ACTIVATED - after registration and confirm through email link (not verified by admin yet)
 * VERIFIED - verified by admin
 * BLOCKED - blocked by admin
 */
public enum StatusType {
    NOT_ACTIVATED,
    ACTIVATED,
    VERIFIED,
    BLOCKED
}
