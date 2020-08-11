const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.admin.token,
  userData: state => state.admin.userData,
  roles: state => state.admin.roles,
  permission_routes: state => state.permission.routes
}
export default getters
