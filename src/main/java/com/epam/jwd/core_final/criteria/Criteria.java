package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private Long id;
    private String name;

    public Criteria(){}

    public Long getId() {
        return id;
    }

    private String getName() { return name; }

    protected Criteria(final Builder<? extends Builder<T>> builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static abstract class Builder<T> {
        private Long id;
        private String name;

        public Builder<T> setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder<T> setName(String name) {
            this.name = name;
            return this;
        }

        public abstract T build();
    }
}
