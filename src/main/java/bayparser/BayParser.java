
package bayparser;

import java.io.IOException;

import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.surround.parser.QueryParser;
import org.apache.lucene.queryparser.surround.query.BasicQueryFactory;
import org.apache.lucene.queryparser.surround.parser.ParseException;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.index.query.QueryParseContext;
import org.elasticsearch.index.query.QueryParsingException;
import org.elasticsearch.index.mapper.MappedFieldType;

public class BayParser implements org.elasticsearch.index.query.QueryParser {

    private final ESLogger logger;

    @Inject
    public BayParser(Settings settings) {
        logger = Loggers.getLogger(getClass(), settings);
    }

    @Override
    public String[] names() {
        String name = "bay_query";
        return new String[] { name, Strings.toCamelCase(name) };
    }

    @Override
    public Query parse(QueryParseContext parseContext) throws IOException, QueryParsingException {
        XContentParser parser = parseContext.parser();
        XContentParser.Token token = parser.currentToken();
        if (token == XContentParser.Token.START_OBJECT) {
            token = parser.nextToken();
        }
        assert token == XContentParser.Token.FIELD_NAME;
        String fieldName = parser.currentName();

        String value = null;
        String queryString = null;
        token = parser.nextToken();
        if (token == XContentParser.Token.START_OBJECT) {
            String currentFieldName = null;
            while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                if ("test_arg".equals(currentFieldName)) { //just to leave a arg for more develop.
                    value = parser.text();
                } else {
                    throw new QueryParsingException(parseContext, "[bay_term] query does not support [" + currentFieldName + "]");
                }
            }
            parser.nextToken();
        } else {
            queryString= parser.text();
            // move to the next token
            parser.nextToken();
            logger.info("query string for bay_parser is " + queryString);
        }

        Query query = null;
        try{
            query = QueryParser.parse(queryString).makeLuceneQueryField(fieldName, new BasicQueryFactory());
        }  catch (ParseException e) {
            throw new QueryParsingException(parseContext, "Failed to parse query [" + queryString + "]", e);
        }

        return query;
    }

}
