package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private final Long id;
    private final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected Criteria(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    protected abstract static class CriteriaBuilder<T extends BaseEntity> {
        private Long id;
        private String name;

        protected Long getId() {
            return id;
        }

        protected String getName() {
            return name;
        }

        public void withId(final Long id) {
            assert id != null;
            this.id = id;
        }

        public void withName(final String name) {
            assert name != null;
            this.name = name;
        }
    }
}
