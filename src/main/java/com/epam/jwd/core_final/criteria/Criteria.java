package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.BaseEntity;

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

    protected Criteria() {
    }

    public static class Builder<T extends AbstractBaseEntity> {
        private Long id = 0L;
        private String name;
        private T t;

        public Builder() {
        }

        public Builder<T> setName(String val) {
            name = val;
            return this;
        }

        public Long getId() {
            id++;
            return id;
        }

        public String getName() {
            return name;
        }

        public T build() {
            return t;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
