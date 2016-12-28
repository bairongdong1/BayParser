
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

