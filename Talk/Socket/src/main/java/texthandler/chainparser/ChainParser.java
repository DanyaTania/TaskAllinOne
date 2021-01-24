package texthandler.chainparser;

import texthandler.entity.TextComposite;


public interface ChainParser {
    TextComposite parse(String from, TextComposite to);
}
