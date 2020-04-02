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

package com.graphaware.example.plugin;

import com.graphaware.test.integration.GraphAwareIntegrationTest;
import org.junit.Test;

/**
 * {@link GraphAwareIntegrationTest} for {@link HelloWorldServerPlugin}.
 * <p/>
 * Only tests the actual deployment of the extension, not so much the logic.
 */
public class HelloWorldServerPluginDeploymentTest extends GraphAwareIntegrationTest {

    @Test
    public void shouldCreateAndReturnNode() {
        httpClient.get(baseNeoUrl() + "/db/data/ext/HelloWorldServerPlugin/graphdb/hello_world_node", 200);
        httpClient.post(baseNeoUrl() + "/db/data/ext/HelloWorldServerPlugin/graphdb/hello_world_node", 200);
    }
}
