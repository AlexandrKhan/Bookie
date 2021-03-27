package edu.epam.bookie.command;

public enum PagePath {
    HOME ("/jsp/home.jsp", "/controller?command=home"),
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

    public String getDirectUrl() {
        return directUrl;
    }

    public String getServletPath() {
        return servletPath;
    }
}
