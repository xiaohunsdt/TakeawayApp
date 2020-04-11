export function addEvent(vue, target, name) {
  naver.maps.Event.addListener(target, name, event => vue.$emit(name, event))
}

export function addClickEvent(vue, target, name, order) {
  naver.maps.Event.addListener(target, name, event => vue.$emit(name, {event, order}))
}
