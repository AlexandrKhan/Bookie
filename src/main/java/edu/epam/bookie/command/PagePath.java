package edu.epam.bookie.command;

/**
 * Enum of paths to page, can be:
 * 1) directUrl to jsp page
 * 2) servletPath executing servlet command
 */

public enum PagePath {
    AUTHORISATION ("/jsp/auth.jsp","/controller?command=authorisation"),
    ADMIN ("/jsp/admin.jsp", "/controller?command=admin_panel"),
    MATCHES ("/jsp/matchList.jsp", "/controller?command=match_list"),
    CABINET ("/jsp/cabinet.jsp", "/controller?command=personal_cabinet"),
    ADD_MATCH ("/jsp/addMatch.jsp","/controller?command=create_match"),
    ERROR_500 ("/jsp/error/500.jsp",""),
    ERROR_404 ("/jsp/error/404.jsp",""),
    MESSAGES ("/jsp/messages.jsp","/controller?command=messages");

    private final String directUrl;
    private final String servletPath;

    PagePath(String url, String servlet) {
        this.directUrl = url;
        this.servletPath = servlet;
    }

    /**
     * Get direct url
     *
     * @return direct url
     */
    public String getDirectUrl() {
        return directUrl;
    }

    /**
     * Get servlet path
     *
     * @return servlet path
     */
    public String getServletPath() {
        return servletPath;
    }
}
