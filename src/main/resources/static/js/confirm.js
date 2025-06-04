function confirmarSuscripcion(opcion) {
	if (opcion==1){
		return confirm('¿Estás seguro de que quieres suscribirte a este evento?');
	}
	if (opcion==2){
		return confirm('¿Estás seguro de que quieres eliminar este evento?');
	}
	if (opcion==3){
		return confirm('¿Estás seguro de que quieres eliminar esta persona?');
	}
	if (opcion==4){
		return confirm('¿Estás seguro de que quieres eliminar esta oferta?');
	}
	if (opcion==5){
		return confirm('¿Estás seguro de que quieres eliminar esta reserva?');
	}
	if (opcion==6){
		return confirm('¿Estás seguro de que quieres eliminar esta suscripcion?');
	}
			
	return confirm('¿Estás seguro de que quieres eliminar este mensaje?')
    
  }