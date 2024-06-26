/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.83
 * Generated at: 2024-04-05 05:51:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class trickNumber_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPではGET、POST、またはHEADのみが許可されます。 JasperはOPTIONSも許可しています。");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write('\n');

ArrayList<Integer> firstNumberList = (ArrayList<Integer>)request.getAttribute("firstNumberList");
ArrayList<Integer> secondNumberList = (ArrayList<Integer>)request.getAttribute("secondNumberList");

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("	<head>\n");
      out.write("		<title>数字探しゲーム</title>\n");
      out.write("		<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print( request.getContextPath() );
      out.write("/css/trickNumber.css\">\n");
      out.write("		\n");
      out.write("		<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("			var start = new Date();\n");
      out.write("			// 初期化\n");
      out.write("			var hour = 0;\n");
      out.write("			var min = 0;\n");
      out.write("			var sec = 0;\n");
      out.write("			var now = 0;\n");
      out.write("			var datet = 0;\n");
      out.write("			var time = 0;\n");
      out.write("			\n");
      out.write("			function disp(){\n");
      out.write("				now = new Date();\n");
      out.write("				datet = parseInt((now.getTime() - start.getTime()) / 1000);			\n");
      out.write("				hour = parseInt(datet / 3600);\n");
      out.write("				min = parseInt((datet / 60) % 60);\n");
      out.write("				sec = datet % 60;\n");
      out.write("							\n");
      out.write("				// 数値が1桁の場合、頭に0を付けて2桁で表示する指定\n");
      out.write("				if(hour < 10) { hour = \"0\" + hour; }\n");
      out.write("				if(min < 10) { min = \"0\" + min; }\n");
      out.write("				if(sec < 10) { sec = \"0\" + sec; }\n");
      out.write("\n");
      out.write("				// フォーマットを指定（不要な行を削除する）\n");
      out.write("				time = hour + ':' + min + ':' + sec; // パターン1\n");
      out.write("			\n");
      out.write("				// テキストフィールドにデータを渡す処理（不要な行を削除する）\n");
      out.write("				var element = document.getElementById('time');\n");
      out.write("				element.innerHTML = time;\n");
      out.write("				\n");
      out.write("				setTimeout(\"disp()\", 1000);		\n");
      out.write("			}\n");
      out.write("		</script>\n");
      out.write("		\n");
      out.write("	</head>\n");
      out.write("\n");
      out.write("	<body onload=\"disp()\">\n");
      out.write("		<h2>数字を探せ<span class =\"correct\" id=\"correct\">31</span></h2>\n");
      out.write("		");
 if(firstNumberList != null) { 
      out.write("\n");
      out.write("			<div class = \"container\">\n");
      out.write("				<p class = \"relative\">この中の不動の数字を小さい順に並べたときに真ん中に来る数字を選べ</p>\n");
      out.write("				\n");
      out.write("				<p>経過時間：<span id=\"time\"></span></p>\n");
      out.write("						\n");
      out.write("				<button type=\"button\" onclick=\"location.href='");
      out.print( request.getContextPath() );
      out.write("/trickNumber'\">リトライ</button>\n");
      out.write("				<div class = \"aa\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(0) );
      out.write("</div>\n");
      out.write("				<div class = \"ab\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(1) );
      out.write("</div>\n");
      out.write("				<div class = \"ac\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(2) );
      out.write("</div>\n");
      out.write("				<div class = \"ad\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(3) );
      out.write("</div>\n");
      out.write("				<div class = \"ae\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(4) );
      out.write("</div>\n");
      out.write("				<div class = \"af\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(5) );
      out.write("</div>\n");
      out.write("				<div class = \"ag\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(6) );
      out.write("</div>\n");
      out.write("				<div class = \"ah\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(7) );
      out.write("</div>\n");
      out.write("				<div class = \"ai\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(8) );
      out.write("</div>\n");
      out.write("				<div class = \"aj\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(9) );
      out.write("</div>\n");
      out.write("				<div class = \"ak\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(10) );
      out.write("</div>\n");
      out.write("				<div class = \"al\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(11) );
      out.write("</div>\n");
      out.write("				<div class = \"am\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(12) );
      out.write("</div>\n");
      out.write("				<div class = \"an\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(13) );
      out.write("</div>\n");
      out.write("				<div class = \"ao\" onclick=\"uncorrect()\">");
      out.print( firstNumberList.get(14) );
      out.write("</div>\n");
      out.write("				<div class = \"ap\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(0) );
      out.write("</div>\n");
      out.write("				<div class = \"aq\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(1) );
      out.write("</div>\n");
      out.write("				<div class = \"ar\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(2) );
      out.write("</div>\n");
      out.write("				<div class = \"as\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(3) );
      out.write("</div>\n");
      out.write("				<div class = \"at\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(4) );
      out.write("</div>\n");
      out.write("				<div class = \"au\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(5) );
      out.write("</div>\n");
      out.write("				<div class = \"av\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(6) );
      out.write("</div>\n");
      out.write("				<div class = \"aw\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(7) );
      out.write("</div>\n");
      out.write("				<div class = \"ax\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(8) );
      out.write("</div>\n");
      out.write("				<div class = \"ay\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(9) );
      out.write("</div>\n");
      out.write("				<div class = \"az\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(10) );
      out.write("</div>\n");
      out.write("				<div class = \"ba\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(11) );
      out.write("</div>\n");
      out.write("				<div class = \"bb\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(12) );
      out.write("</div>\n");
      out.write("				<div class = \"bc\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(13) );
      out.write("</div>\n");
      out.write("				<div class = \"bd\" onclick=\"uncorrect()\">");
      out.print( secondNumberList.get(14) );
      out.write("</div>\n");
      out.write("			</div>\n");
      out.write("		\n");
      out.write("			<script type=\"text/javascript\">\n");
      out.write("						\n");
      out.write("				var correct = document.getElementById('correct');\n");
      out.write("				\n");
      out.write("				correct.onclick = function() {\n");
      out.write("					alert(\"正解⭕️\" + \"\\n\" + \"時間：\" + time);\n");
      out.write("				}\n");
      out.write("\n");
      out.write("				function uncorrect() {\n");
      out.write("					alert(\"不正解❌\");\n");
      out.write("				}\n");
      out.write("					\n");
      out.write("			</script>\n");
      out.write("		");
 } 
      out.write("\n");
      out.write("	</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
