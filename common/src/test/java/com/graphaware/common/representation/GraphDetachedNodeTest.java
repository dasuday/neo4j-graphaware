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

package com.graphaware.common.representation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class GraphDetachedNodeTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void nodeRepresentationIsCorrectlyConvertedToJson() throws JsonProcessingException, JSONException {
        GraphDetachedNode representation = new GraphDetachedNode(0, new String[]{"Label1, Label2"}, Collections.singletonMap("key", "value"));
        String actualStr = mapper.writeValueAsString(representation);
        System.out.println(actualStr);
        JSONAssert.assertEquals("{\"graphId\":0,\"properties\":{\"key\":\"value\"},\"labels\":[\"Label1, Label2\"]}", actualStr, true);
    }

    @Test
    public void nodeRepresentationIsCorrectlyConvertedFromJson() throws IOException, JSONException {
        String json = "{\"graphId\":0,\"properties\":{\"key\":\"value\"},\"labels\":[\"Label1, Label2\"]}";
        assertEquals(new GraphDetachedNode(0, new String[]{"Label1, Label2"}, Collections.singletonMap("key", "value")), mapper.readValue(json, GraphDetachedNode.class));
    }
}
