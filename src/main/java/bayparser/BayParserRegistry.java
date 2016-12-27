package bayparser;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryParser;

import java.util.Map;
import java.util.Set;

public class BayParserRegistry extends AbstractComponent {

    private ImmutableMap<String, QueryParser> queryParsers;

    @Inject
    public BayParserRegistry(Settings settings, Set<QueryParser> injectedQueryParsers) {
        super(settings);
        Map<String, QueryParser> queryParsers = Maps.newHashMap();
        for (QueryParser queryParser : injectedQueryParsers) {
            for (String name : queryParser.names()) {
                queryParsers.put(name, queryParser);
            }
        }
        this.queryParsers = ImmutableMap.copyOf(queryParsers);
    }

    /**
     * Returns all the registered query parsers
     */
    public ImmutableMap<String, QueryParser> queryParsers() {
        return queryParsers;
    }
}
