package com.challenge.techbase.util;

public class Enum {

    public enum RoleId {
        DIRECTOR(1), MANAGER(2), MEMBER(3);

        public Integer id;

        private RoleId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return this.id;
        }
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    public enum Status {
        ACTIVE, DELETED
    }

    public enum SortDirection {
        DESC(0), ASC(1);

        private Integer value;

        public Integer getValue() {
            return this.value;
        }

        private SortDirection(Integer value) {
            this.value = value;
        }
    }
}
