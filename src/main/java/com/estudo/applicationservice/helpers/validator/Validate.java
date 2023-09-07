package com.estudo.applicationservice.helpers.validator;

public class Validate {

    private boolean userPresenceExist = false;
    private boolean userFrequencyExist = false;

    private boolean itsNewClass = false;
    public Validate() {
    }

    public Validate(
            final boolean userPresenceExist,
            final boolean userFrequencyExist,
            final boolean itsNewClass) {
        this.userPresenceExist = userPresenceExist;
        this.userFrequencyExist = userFrequencyExist;
        this.itsNewClass = itsNewClass;
    }

    public boolean userPresenceExist() {
        return userPresenceExist;
    }

    public void setUserPresenceExist(boolean userPresenceExist) {
        this.userPresenceExist = userPresenceExist;
    }

    public boolean userFrequencyExist() {
        return userFrequencyExist;
    }

    public void setUserFrequencyExist(boolean userFrequencyExist) {
        this.userFrequencyExist = userFrequencyExist;
    }

    public boolean itsNewClass() {
        return itsNewClass;
    }

    public void setItsNewClass(boolean itsNewClass) {
        this.itsNewClass = itsNewClass;
    }
}
