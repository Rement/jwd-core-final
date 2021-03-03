package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends AbstractBaseEntity> {
    private Long id;
    private String name;

    protected Criteria(Builder<?> builder) {
        id = builder.id;
        name = builder.name;
    }

    public static class Builder<T extends AbstractBaseEntity> {
        private Long id;
        private String name;
        // private CrewMember crewMember=new CrewMember();
        private T t;

        public Builder() {
        }

        public Builder<T> setId(Long val) {
            id = val;
            return this;
        }

        public Builder<T> setName(String val) {
            name = val;
            return this;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public T build() {
            return t;
        }
    }
}
