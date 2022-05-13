package com.csc510.resa.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Request Matcher that skips URL paths instead of matching them
 *
 * @author Steven Green
 */
public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher matchers;
    private RequestMatcher processingMatcher;

    /**
     * Initializes the skip request matcher
     *
     * @param pathsToSkip    A list of paths to skip
     * @param processingPath The root path to apply the request matcher
     */
    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        List<RequestMatcher> m = new ArrayList<RequestMatcher>();

        for (String path : pathsToSkip) {
            m.add(new AntPathRequestMatcher(path));
        }

        matchers = new OrRequestMatcher(m);
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (matchers.matches(request)) {
            return false;
        }
        return processingMatcher.matches(request);
    }
}
