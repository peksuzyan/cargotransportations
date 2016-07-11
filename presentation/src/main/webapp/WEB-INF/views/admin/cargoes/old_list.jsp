<c:if test="${not empty cargoes}">
    <table>
        <thead>
        <tr>
            <th>${cargoId}</th>
            <th>${cargoName}</th>
            <th>${cargoWeight}</th>
            <th>${cargoDepartureCity}</th>
            <th>${cargoArrivalCity}</th>
            <th>${cargoStatus}</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cargo" items="${cargoes}">
            <tr>
                <td>${cargo.id}</td>
                <td>${cargo.name}</td>
                <td>${cargo.weight}</td>
                <td>${cargo.departureCity}</td>
                <td>${cargo.arrivalCity}</td>
                <td>${cargo.status}</td>
                <td><a href="/cargoes/${cargo.id}">${appButtonEdit}</a></td>
                <td><a href="/cargoes/${cargo.id}?delete">${appButtonDelete}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>