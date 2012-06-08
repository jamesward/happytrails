<%@ page import="happytrails.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'value', 'error')} required">
	<label for="value">
		<g:message code="comment.value.label" default="Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="value" required="" value="${commentInstance?.value}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'creationDate', 'error')} ">
	<label for="creationDate">
		<g:message code="comment.creationDate.label" default="Creation Date" />
		
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${commentInstance?.creationDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="comment.photo.label" default="Photo" />
		
	</label>
	<g:select id="photo" name="photo.id" from="${happytrails.Photo.list()}" optionKey="id" value="${commentInstance?.photo?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'route', 'error')} required">
	<label for="route">
		<g:message code="comment.route.label" default="Route" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="route" name="route.id" from="${happytrails.Route.list()}" optionKey="id" required="" value="${commentInstance?.route?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="comment.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${happytrails.User.list()}" optionKey="id" required="" value="${commentInstance?.user?.id}" class="many-to-one"/>
</div>

