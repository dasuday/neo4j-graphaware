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

package com.graphaware.tx.event.improved.entity.snapshot;

import com.graphaware.common.wrapper.RelationshipWrapper;
import com.graphaware.tx.event.improved.data.EntityTransactionData;
import com.graphaware.tx.event.improved.data.TransactionDataContainer;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.logging.Log;
import com.graphaware.common.log.LoggerFactory;

/**
 * A {@link EntitySnapshot} representing a {@link org.neo4j.graphdb.Relationship}.
 */
public class RelationshipSnapshot extends EntitySnapshot<Relationship> implements Relationship, RelationshipWrapper {
    private static final Log LOG = LoggerFactory.getLogger(RelationshipSnapshot.class);

    /**
     * Construct a snapshot.
     *
     * @param wrapped                  relationship.
     * @param transactionDataContainer transaction data container.
     */
    public RelationshipSnapshot(Relationship wrapped, TransactionDataContainer transactionDataContainer) {
        super(wrapped, transactionDataContainer);
    }

    @Override
    protected EntityTransactionData<Relationship> transactionData() {
        return transactionDataContainer.getRelationshipTransactionData();
    }

    @Override
    protected Relationship self() {
        return this;
    }

    @Override
    public void delete() {
        if (transactionDataContainer.getRelationshipTransactionData().hasBeenDeleted(wrapped)) {
            LOG.warn("Relationship " + getId() + " has already been deleted in this transaction.");
        } else {
            super.delete();
        }
    }

    @Override
    protected Node wrapNode(Node node) {
        return new NodeSnapshot(node, transactionDataContainer);
    }
}
