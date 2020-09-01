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

package com.graphaware.runtime.bootstrap;

import org.neo4j.configuration.Config;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.extension.ExtensionFactory;
import org.neo4j.kernel.extension.ExtensionType;
import org.neo4j.kernel.extension.context.ExtensionContext;
import org.neo4j.kernel.lifecycle.Lifecycle;
import org.neo4j.logging.internal.LogService;

/**
 * {@link ExtensionFactory} that initializes the {@link RuntimeKernelExtension}.
 */
public class RuntimeExtensionFactory extends ExtensionFactory<RuntimeExtensionFactory.Dependencies> {

    public interface Dependencies {
        Config getConfig();

        GraphDatabaseService getDatabase();

        DatabaseManagementService managementService();

        LogService getLogging();
    }

    public static final String KEY = "GraphAware Runtime";

    public RuntimeExtensionFactory() {
        super(ExtensionType.DATABASE, KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lifecycle newInstance(ExtensionContext extensionContext, Dependencies dependencies) {
        return new RuntimeKernelExtension(dependencies.getConfig(), dependencies.managementService(), dependencies.getDatabase());
    }
}
