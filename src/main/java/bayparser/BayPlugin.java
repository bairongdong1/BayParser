/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package bayparser;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.common.util.ExtensionPoint;
import org.elasticsearch.index.query.QueryParser;

import java.util.Collection;
import java.util.Collections;

/**
 *
 */
public class BayPlugin extends Plugin {

    @Override
    public String name() {
        return "bay-parser";
    }

    @Override
    public String description() {
        return "support for surround analyize";
    }

    @Override
    public Collection<Module> nodeModules() {
        return Collections.<Module>singletonList(new BayModule());
    }

    public void onModule(Module module) {
    }

    public static class BayModule extends AbstractModule {
        private final ExtensionPoint.ClassSet<QueryParser> queryParsers
        = new ExtensionPoint.ClassSet<>("query_parser_2", QueryParser.class);

        @Override
        protected void configure() {
            queryParsers.registerExtension(BayParser.class);
            queryParsers.bind(binder());
            bind(BayParserRegistry.class).asEagerSingleton();
        }
    }
}

