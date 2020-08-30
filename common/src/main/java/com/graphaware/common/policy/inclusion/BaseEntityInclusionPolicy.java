/*
 * Copyright (c) 2013-2020 GraphAware
 *
 * This file is part of the GraphAware Framework.
 *
 * GraphAware Framework is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package com.graphaware.common.policy.inclusion;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Entity;
import org.neo4j.helpers.collection.FilteringIterable;

/**
 * Base class for {@link EntityInclusionPolicy} implementations that implement the {@link #getAll(GraphDatabaseService)}
 * method in a naive way.
 *
 * @param <T>
 */
public abstract class BaseEntityInclusionPolicy<T extends Entity> implements EntityInclusionPolicy<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<T> getAll(GraphDatabaseService database) {
        return new FilteringIterable<>(doGetAll(database), this::include);
    }

    /**
     * Simply get all possible {@link Entity}s from the database, not worrying whether they are included by
     * this policy or not.
     *
     * @param database to get entities from.
     * @return all entities.
     */
    protected abstract Iterable<T> doGetAll(GraphDatabaseService database);
}
