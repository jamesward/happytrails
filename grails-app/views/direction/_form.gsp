<%@ page import="happytrails.Direction" %>

<g:hiddenField name="route.id" value="${directionInstance.route?.id}"/>

<div class="fieldcontain ${hasErrors(bean: directionInstance, field: 'stepNumber', 'error')} required">
	<label for="stepNumber">
		<g:message code="direction.stepNumber.label" default="Step Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" style="width: 40px" name="stepNumber" required="" value="${fieldValue(bean: directionInstance, field: 'stepNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: directionInstance, field: 'instruction', 'error')} required">
	<label for="instruction">
		<g:message code="direction.instruction.label" default="Instruction" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField style="width: 600px" name="instruction" required="" value="${directionInstance?.instruction}"/>
</div>

