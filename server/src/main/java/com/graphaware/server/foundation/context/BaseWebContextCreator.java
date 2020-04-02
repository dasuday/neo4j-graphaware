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

package com.graphaware.server.foundation.context;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.neo4j.kernel.configuration.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public abstract class BaseWebContextCreator implements WebContextCreator {

    @Override
    public WebApplicationContext createWebContext(ApplicationContext rootContext, ServletContextHandler handler, Config config) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setParent(rootContext);
        context.setServletContext(handler.getServletContext());

        registerConfigClasses(context, config);

        context.refresh();

        return context;
    }

    protected abstract void registerConfigClasses(AnnotationConfigWebApplicationContext context, Config config);
}
