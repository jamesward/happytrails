package happytrails

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class RegionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [regionInstanceList: Region.list(params), regionInstanceTotal: Region.count()]
    }

    def create() {
        [regionInstance: new Region(params)]
    }

    def save() {
        def regionInstance = new Region(params)
        if (!regionInstance.save(flush: true)) {
            render(view: "create", model: [regionInstance: regionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.name])
        redirect(action: "show", id: regionInstance.id)
    }

    def find() {
        if (params.region == "login") {
            redirect(controller: "login", action: "auth")
            return
        }

        println("finding by name: " + params.region)

        if (params.route) {
            forward controller: "route", action: "find", params: [route: params.route]
            return
        }
        def regionInstance = Region.findBySeoName(params.region)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        render(view: "show", model: [regionInstance: regionInstance])
    }

    def show() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        // todo: sort routes based on sort/order parameters

        def subscriptionId = getSubscriptionId(regionInstance)

        [regionInstance: regionInstance, subscriptionId: subscriptionId]
    }

    private long getSubscriptionId(Region regionInstance) {
        def subscriptionId = 0
        User user = (User) springSecurityService?.currentUser
        if (user != null) {
            for (subs in user.getRegionSubscriptions()) {
                if (subs.getRegion().equals(regionInstance)) {
                    subscriptionId = subs.id
                }
            }
        }
        subscriptionId
    }

    def subscribe() {
        def region = Region.get(params.id)
        User user = (User) springSecurityService.currentUser
        def subscription = new RegionSubscription(user, region)
        subscription.save()
        flash.message = "You have subscribed for updates to " + region.name + " successfully."

        redirect(action: "show", params: [id: region.id])
    }

    def deleteSubscription() {
        def sub = RegionSubscription.findById(params.id)
        sub.delete()
        flash.message = "You have unsubscribed from " + sub.region.name + "."

        redirect(action: "show", params: [id: sub.region.id])
    }

    def feed = {
        def region = Region.findBySeoName(params.region)
        if (!region) {
            response.status = 404
            return
        }

        render(feedType: "atom") {
            title = "Happy Trails Feed for " + region.name
            link = createLink(absolute:  true, controller: 'region', action: 'feed', params: ['region', region.seoName])
            description = "New Routes and Reviews for " + region.name
            region.routes.each() { route ->
                entry(route.name) {
                    link = createLink(absolute: true, controller: 'route', action: 'show', id: route.id)
                    route.description
                }
            }
        }
    }

    //@Secured(['ROLE_ADMIN'])
    def edit() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        [regionInstance: regionInstance]
    }

    def update() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (regionInstance.version > version) {
                regionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'region.label', default: 'Region')] as Object[],
                        "Another user has updated this Region while you were editing")
                render(view: "edit", model: [regionInstance: regionInstance])
                return
            }
        }

        regionInstance.properties = params

        if (!regionInstance.save(flush: true)) {
            render(view: "edit", model: [regionInstance: regionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.name])
        redirect(action: "show", id: regionInstance.id)
    }

    def delete() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
            return
        }

        try {
            regionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
