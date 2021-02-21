package com.lania.biblioapp_cli.componentes.miembro.lista;

import androidx.lifecycle.ViewModel;

import com.lania.biblioapp_cli.entidades.Miembro;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MiembroItemViewModel extends ViewModel {
    private final Miembro data;


    public String getElapsedTime() {

        LocalDateTime fromDateTime = this.data.getFechaDeRegistro();
        LocalDateTime toDateTime = LocalDateTime.now();

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);

        StringBuilder stringBuilder = new StringBuilder();

        if (years > 0) {
            stringBuilder.append(years).append(" años, ");
        }
        if (months > 0) {
            stringBuilder.append(months).append(" meses, ");
        }
        if (days > 0) {
            stringBuilder.append(days).append(" dias, ");
        }
        if (hours > 0) {
            stringBuilder.append(hours).append(" horas, ");
        }
        if (minutes > 0) {
            stringBuilder.append(minutes).append(" minutos, ");
        }
        if (seconds > 0) {
            stringBuilder.append(seconds).append(" segundos.");
        }

        String salida = stringBuilder.toString();
        if (salida.isEmpty()) {
            return "Nuevo registro";
        } else return salida;
    }

}
