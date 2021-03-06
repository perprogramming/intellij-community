package com.intellij.remotesdk;

import com.intellij.execution.process.OSProcessManager;

/**
 * @author traff
 */
abstract public class RemoteSshProcess extends Process implements OSProcessManager.SelfKiller {
  /**
   * Makes host:localPort server which is available on local side available on remote side as localhost:remotePort.
   */
  public abstract void addRemoteTunnel(int remotePort, String host, int localPort) throws RemoteInterpreterException;

  /**
   * Makes host:remotePort server which is available on remote side available on local side as localhost:localPort.
   */
  public abstract void addLocalTunnel(int localPort, String host, int remotePort) throws RemoteInterpreterException;

  public abstract boolean hasPty();

  public abstract boolean sendCtrlC();
}
