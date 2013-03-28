import org.codehaus.groovy.runtime.GStringImpl
import org.springframework.web.util.HtmlUtils

class EncodeAsHTMLBootStrap {

	def init = { servletContext ->
		log.debug "Adding custom encodeAsHTML metamethods"
		[String, GStringImpl, StringBuffer, StringBuilder, Object].each { it.metaClass.encodeAsHTML << { -> HtmlUtils.htmlEscape(delegate.toString()) } }
	}
}