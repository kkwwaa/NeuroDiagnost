package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.protocolNotFound

class ProtocolNotFoundException(
    protocolId: Long
) : RuntimeException(protocolNotFound(protocolId))